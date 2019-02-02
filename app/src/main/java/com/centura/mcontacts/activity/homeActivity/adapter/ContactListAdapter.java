package com.centura.mcontacts.activity.homeActivity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.centura.mcontacts.DataSource.room.EntityModels.UserContact;
import com.centura.mcontacts.R;
import com.centura.mcontacts.activity.homeActivity.HomeContract;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Manikandan Baskaran on 02-02-2019.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    private Context mContext;
    private List<UserContact> arrUserContacts = Collections.emptyList();
    private HomeContract.Presenter homePresenter;

    public ContactListAdapter(Context mContext, HomeContract.Presenter homePresenter) {
        this.mContext = mContext;
        this.homePresenter = homePresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.contact_content_adapter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        UserContact userContact = arrUserContacts.get(i);

        viewHolder.tvName.setText(userContact.getContactName());
        viewHolder.tvNumber.setText(userContact.getPhoneNumber());
        viewHolder.cLContact.setOnClickListener(view -> homePresenter.onContactSelected(userContact));
    }

    @Override
    public int getItemCount() {
        return arrUserContacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvNumber)
        TextView tvNumber;

        @BindView(R.id.cLContact)
        RelativeLayout cLContact;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setContacts(List<UserContact> arrUserContacts) {
        this.arrUserContacts = arrUserContacts;
        notifyDataSetChanged();
    }
}
