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
import scheduler.wgu.mywguscheduler.UI.Assessment.AssessmentAdapter;

public class AddCourseAdapter extends RecyclerView.Adapter<AddCourseAdapter.AssessmentViewHolder> {

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
        public void onClick(View v) { }

        public TextView getAssessmentTitle() {
            return assessmentTitle;
        }

    }

    public AddCourseAdapter(LayoutInflater mInflater, List<Assessment> mAssessments, Context context) {
        this.mInflater = mInflater;
        this.mAssessments = mAssessments;
        this.context = context;
    }


    @NonNull
    @Override
    public AddCourseAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_assessment_list_item, parent, false);
        return new AssessmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddCourseAdapter.AssessmentViewHolder holder, int position) {
        holder.getAssessmentTitle().setText();
    }

    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }
}
