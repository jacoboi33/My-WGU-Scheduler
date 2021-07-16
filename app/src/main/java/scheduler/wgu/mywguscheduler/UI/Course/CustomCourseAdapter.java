package scheduler.wgu.mywguscheduler.UI.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.R;

public class CustomCourseAdapter extends RecyclerView.Adapter<CustomCourseAdapter.AssessmentViewHolder> {

    private final LayoutInflater mInflater;
    private List<Assessment> mAssessments;
//    private List<Assessment> selectedAssessments;
    private final Context context;

    private static class AssessmentChecked {
        private Assessment assessment;
        private boolean isChecked;

        public AssessmentChecked(Assessment assessment, boolean isChecked) {
            this.assessment = assessment;
            this.isChecked = isChecked;
        }

        public Assessment getAssessment() {
            return assessment;
        }

        public void setAssessment(Assessment assessment) {
            this.assessment = assessment;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }

    static class AssessmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView assessmentTitle;
        private ImageView imageView;
        private boolean selected = false;
        private List<AssessmentChecked> selectedAssessments = new ArrayList<>();


        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentTitle = itemView.findViewById(R.id.assessment_title);
            imageView = itemView.findViewById(R.id.image_view);
        }

        void bind(final Assessment assessment) {
//            imageView.setVisibility(selected ? View.VISIBLE : View.GONE);
            assessmentTitle.setText(assessment.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected = !selected;
                    selectedAssessments.add(new AssessmentChecked(assessment, selected));
//                    imageView.setVisibility(selected ? View.VISIBLE : View.GONE);
                    Toast.makeText(v.getContext(), assessment.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public TextView getAssessmentTitle() {
            return assessmentTitle;
        }
        public ImageView getImageView() { return imageView; }

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
        holder.bind(mAssessments.get(position));
//        holder.getAssessmentTitle().setText(mAssessments.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }
}
