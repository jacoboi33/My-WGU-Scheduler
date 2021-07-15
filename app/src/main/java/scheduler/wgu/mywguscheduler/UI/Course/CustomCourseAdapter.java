package scheduler.wgu.mywguscheduler.UI.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.R;

public class CustomCourseAdapter extends RecyclerView.Adapter<CustomCourseAdapter.AssessmentViewHolder> {

    private final LayoutInflater mInflater;
    private List<Assessment> mAssessments;
    private final Context context;

    static class AssessmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView assessmentTitle;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentTitle = itemView.findViewById(R.id.assessment_title);
        }

        @Override
        public void onClick(View v) {
            
        }

        public TextView getAssessmentTitle() {
            return assessmentTitle;
        }

    }

    public CustomCourseAdapter(Context context, List<Assessment> mAssessments) {
        mInflater = LayoutInflater.from(context);
        this.mAssessments = mAssessments;
        this.context = context;
    }

    public void setAssessments(List<Assessment> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CustomCourseAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_assessment_list_item, parent, false);
        return new AssessmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomCourseAdapter.AssessmentViewHolder holder, int position) {
        holder.getAssessmentTitle().setText(mAssessments.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }
}
