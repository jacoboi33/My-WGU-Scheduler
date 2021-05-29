package scheduler.wgu.mywguscheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.InstructorEntity;
import scheduler.wgu.mywguscheduler.R;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorViewHolder> {

    private final LayoutInflater mInflater;
    private List<InstructorEntity> mInstructors;
    private final Context context;


    public InstructorAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    class InstructorViewHolder extends RecyclerView.ViewHolder {
        private final TextView instructorNameItemView;
        private final TextView instructorEmailItemView;
        private final TextView instructorPhoneNumberItemView;


        private InstructorViewHolder(View itemView) {
            super(itemView);
            instructorNameItemView = itemView.findViewById(R.id.instructorNameTextView);
            instructorEmailItemView = itemView.findViewById(R.id.instructorEmailTextView);
            instructorPhoneNumberItemView = itemView.findViewById(R.id.instructorPhoneNumberTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    final InstructorEntity current = mInstructors.get(position);
                    Intent intent = new Intent(context, InstructorActivity.class);
                    intent.putExtra("name", current.getName());
                    intent.putExtra("email", current.getEmailAddress());
                    intent.putExtra("productID", current.getPhoneNumber());
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }


    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.instructor_list_item, parent, false);
        return new InstructorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InstructorViewHolder holder, int position) {
        if (mInstructors != null) {
            final InstructorEntity current = mInstructors.get(position);
            holder.instructorNameItemView.setText(current.getName());
            holder.instructorEmailItemView.setText(current.getEmailAddress());
            holder.instructorPhoneNumberItemView.setText(current.getPhoneNumber());
        } else {
            holder.instructorNameItemView.setText("No Name");
            holder.instructorEmailItemView.setText("No Email");
            holder.instructorPhoneNumberItemView.setText("No Phone Number");
        }
    }

    @Override
    public int getItemCount() {
        if (mInstructors != null)
            return mInstructors.size();
        else {
            return 0;
        }
    }

    public void setWords(List<InstructorEntity> words) {
        mInstructors = words;
        notifyDataSetChanged();
    }
}
