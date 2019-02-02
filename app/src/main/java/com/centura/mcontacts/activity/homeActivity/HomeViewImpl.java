package com.centura.mcontacts.activity.homeActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.centura.mcontacts.DataSource.room.EntityModels.User;
import com.centura.mcontacts.DataSource.room.EntityModels.UserContact;
import com.centura.mcontacts.R;
import com.centura.mcontacts.activity.homeActivity.adapter.ContactListAdapter;
import com.centura.mcontacts.activity.loginActivity.LoginViewImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.centura.mcontacts.activity.loginActivity.LoginViewImpl.USER_CLASS;

/**
 * Created by Manikandan Baskaran on 02-02-2019.
 */
public class HomeViewImpl extends AppCompatActivity implements HomeContract.View {


    /*@BindView(R.id.bottom_sheet)
    LinearLayout bottom_sheet;

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.etNumber)
    EditText etNumber;

    @BindView(R.id.btnEdit)
    TextView btnEdit;

    @BindView(R.id.btnSave)
    TextView btnSave;

    @BindView(R.id.btnDelete)
    TextView btnDelete;

    @BindView(R.id.btnEditSave)
    TextView btnEditSave;



    @BindView(R.id.tvTitle)
    TextView tvTitle;

*/


    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.rvContacts)
    RecyclerView rvContacts;

    @BindView(R.id.ivLogout)
    ImageView ivLogout;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private BottomSheetDialog mBottomSheetDialog;

    private HomeModelImpl homeModelImpl;
    private HomeContract.Presenter homePresenter;

    private String ownerEmail;

    private UserContact selectedContact;

    private EditText etName, etNumber;
    private TextView tvTitle;
    private Button btnEdit, btnSave, btnDelete, btnEditSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);
        homeModelImpl = ViewModelProviders.of(this).get(HomeModelImpl.class);
        homePresenter = new HomePresenterImpl(this, homeModelImpl);
        init();
        onClicks();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(USER_CLASS);

        tvUserName.setText(String.format("%s's Contacts", user.getUserName()));

        ownerEmail = user.getMailId();
        mBottomSheetDialog = new BottomSheetDialog(this);

        View sheetView = getLayoutInflater().inflate(R.layout.edit_contact_bottom_sheet, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.setOnDismissListener(dialogInterface -> fab.show());

        etName = sheetView.findViewById(R.id.etName);
        etNumber = sheetView.findViewById(R.id.etNumber);
        btnEdit = sheetView.findViewById(R.id.btnEdit);
        btnSave = sheetView.findViewById(R.id.btnSave);
        btnDelete = sheetView.findViewById(R.id.btnDelete);
        btnEditSave = sheetView.findViewById(R.id.btnEditSave);
        tvTitle = sheetView.findViewById(R.id.tvTitle);

        ContactListAdapter contactListAdapter = new ContactListAdapter(this, homePresenter);
        rvContacts.setAdapter(contactListAdapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        homeModelImpl.getUserContacts(ownerEmail).observe(this,
                contactListAdapter::setContacts);
    }

    @Override
    public void onClicks() {
        fab.setOnClickListener(view -> homePresenter.OnFabClick());

        btnSave.setOnClickListener(view -> homePresenter.onSave(
                ownerEmail
                , String.valueOf(etName.getText())
                , String.valueOf(etNumber.getText())
        ));

        btnDelete.setOnClickListener(view -> homePresenter.deleteContact(selectedContact));

        btnEdit.setOnClickListener(view -> homePresenter.onEnableEditClick());

        btnEditSave.setOnClickListener(view -> homePresenter.onEdit(selectedContact
                , String.valueOf(etName.getText())
                , String.valueOf(etNumber.getText())));

        ivLogout.setOnClickListener(view -> homePresenter.onLogoutClick());

    }

    @Override
    public void setNameError(String message) {
        etName.requestFocus();
        etName.setError(message);
    }

    @Override
    public void setNumberError(String message) {
        etNumber.requestFocus();
        etNumber.setError(message);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNewContact() {
        tvTitle.setText(R.string.add_contact_title);
        etName.setText("");
        etNumber.setText("");
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        btnEditSave.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
        mBottomSheetDialog.show();
        fab.hide();
    }

    @Override
    public void showEditContact(UserContact user) {

        selectedContact = user;
        tvTitle.setText(R.string.view_contact);
        etName.setText(user.getContactName());
        etNumber.setText(user.getPhoneNumber());
        btnEdit.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.VISIBLE);
        btnEditSave.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
        mBottomSheetDialog.show();
        fab.hide();
    }

    @Override
    public void hideBottomSheet() {
        etName.setText("");
        etNumber.setText("");
        mBottomSheetDialog.dismiss();
        fab.show();
    }

    @Override
    public void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showKeyBoard() {
        etName.requestFocus();
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null)
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    @Override
    public void disableEditing() {
        btnEdit.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.VISIBLE);
        btnEditSave.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
    }

    @Override
    public void enableEditing() {

        tvTitle.setText(R.string.edit_contact);
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        btnEditSave.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
    }

    @Override
    public void logOut() {
        Intent intent = new Intent(HomeViewImpl.this, LoginViewImpl.class);
        startActivity(intent);
        finish();
    }
}