package scheduler.wgu.mywguscheduler.UI;

import android.content.Context;
import android.content.Intent;
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
    private final Context context;

    public AssessmentAdapter(Context context, HandleAssessmentClick clickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        this.context = context;
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView type;
        private final TextView dueDate;
        private final TextView courseTitle;

        private final Button deleteAssessmentButton;
        private final Button editAssessmentButton;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            dueDate = itemView.findViewById(R.id.due_date);
            courseTitle = itemView.findViewById(R.id.course_title);

            deleteAssessmentButton = itemView.findViewById(R.id.delete_assessment);
            editAssessmentButton = itemView.findViewById(R.id.edit_assessment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetailActivity.class);
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("type", current.getType());
                    intent.putExtra("position", position);
                    intent.putExtra("id", current.getId());
                    intent.putExtra("courseId", current.getCourseId());
                    context.startActivity(intent);
                }
            });
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
            holder.courseTitle.setText(Integer.toString(current.getCourseId()));
        } else {
            holder.title.setText("No title");
            holder.courseTitle.setText("No Courses Added");
            holder.type.setText("No Assessment Type Selected");
            holder.dueDate.setText("No Date");
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
