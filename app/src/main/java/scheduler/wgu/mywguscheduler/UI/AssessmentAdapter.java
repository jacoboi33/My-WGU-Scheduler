package scheduler.wgu.mywguscheduler.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.R;


public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private final LayoutInflater mInflater;
    private List<Assessment> mAssessments;
    private HandleAssessmentClick clickListener;

    public AssessmentAdapter(Context context, HandleAssessmentClick clickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView type;
        private final TextView dueDate;

        private final Button deleteAssessmentButton;
        private final Button editAssessmentButton;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            dueDate = itemView.findViewById(R.id.due_date);

            deleteAssessmentButton = itemView.findViewById(R.id.delete_assessment);
            editAssessmentButton = itemView.findViewById(R.id.edit_assessment);
        }
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessment current = mAssessments.get(position);
            holder.title.setText(current.getTitle());
            holder.type.setText(current.getType());
            holder.dueDate.setText(current.getEndDate());
        } else {
            holder.title.setText("No title");
            holder.title.setText("No Type");
            holder.title.setText("No Date");
        }

        holder.deleteAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeAssessments(mAssessments.get(position));
            }
        });

        holder.editAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editAssessments(mAssessments.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else
            return 0;
    }

    public void setWords(List<Assessment> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }

    public interface HandleAssessmentClick {
        void removeAssessments(Assessment assessment);
        void editAssessments(Assessment assessment);
    }
}
