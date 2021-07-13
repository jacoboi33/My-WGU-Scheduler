package scheduler.wgu.mywguscheduler.UI.Instructor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.R;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorViewHolder> {

    private final LayoutInflater mInflater;
    private List<Instructor> mInstructors;
    private HandleInstructorClick clickListener;
    private final Context context;


    public InstructorAdapter(Context context, HandleInstructorClick clickListener) {
        mInflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        this.context = context;
    }

    public InstructorAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    class InstructorViewHolder extends RecyclerView.ViewHolder {
        private final TextView instructorNameItemView;
        private final TextView instructorEmailItemView;
        private final TextView instructorPhoneNumberItemView;

        private final Button deleteInstructorButton;
        private final Button editInstructorButton;


        private InstructorViewHolder(View itemView) {
            super(itemView);
            instructorNameItemView = itemView.findViewById(R.id.instructor_name);
            instructorEmailItemView = itemView.findViewById(R.id.instructor_email);
            instructorPhoneNumberItemView = itemView.findViewById(R.id.instructor_phone_number);
            deleteInstructorButton = itemView.findViewById(R.id.delete_instructor);
            editInstructorButton = itemView.findViewById(R.id.edit_instructor);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAbsoluteAdapterPosition();
//                    final Instructor current = mInstructors.get(position);
//                    Intent intent = new Intent(context, InstructorActivity.class);
//                    intent.putExtra("name", current.getName());
//                    intent.putExtra("email", current.getEmailAddress());
//                    intent.putExtra("phoneNumber", current.getPhoneNumber());
//                    intent.putExtra("position", position);
//                    context.startActivity(intent);
//                }
//            });
        }
    }


    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.instructor_list_item, parent, false);
//        Button
        return new InstructorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InstructorViewHolder holder, int position) {

        if (mInstructors != null) {
//            final InstructorViewModel current = mInstructors.get(position);
            Instructor current = mInstructors.get(position);
            holder.instructorNameItemView.setText(current.getName());
            holder.instructorEmailItemView.setText(current.getEmailAddress());
            holder.instructorPhoneNumberItemView.setText(current.getPhoneNumber());
//            holder.bind
//            holder.getBindingAdapter(current.getAllInstructors());
//            holder.bind(current.getWord());

//            holder.instructorNameItemView.setText(current.getAllInstructors().observe(););
//            holder.instructorEmailItemView.setText(current.getEmailAddress());
//            holder.instructorPhoneNumberItemView.setText(current.getPhoneNumber());
        } else {
            holder.instructorNameItemView.setText("No Name");
            holder.instructorEmailItemView.setText("No Email");
            holder.instructorPhoneNumberItemView.setText("No Phone Number");
        }

        holder.deleteInstructorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeInstructor(mInstructors.get(position));
            }
        });

        holder.editInstructorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editInstructor(mInstructors.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mInstructors != null)
            return mInstructors.size();
        else {
            return 0;
        }
    }

    public void setWords(List<Instructor> instructors) {
        mInstructors = instructors;
        notifyDataSetChanged();
    }

    public List<Instructor> getWords() {
        notifyDataSetChanged();
        return mInstructors;
    }

    public interface HandleInstructorClick {
        void removeInstructor(Instructor instructor);
        void editInstructor(Instructor instructor);
    }
}
