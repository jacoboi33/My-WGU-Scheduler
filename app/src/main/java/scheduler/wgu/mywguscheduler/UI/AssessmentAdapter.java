package scheduler.wgu.mywguscheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.*;
import java.util.zip.Inflater;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.Entity.Course;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.AssessmentViewModel;


public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private final LayoutInflater mInflater;
    private List<Assessment> mAssessments;
    private List<Course> mCourses;
    private HandleAssessmentClick clickListener;
    private final Context context;

    public AssessmentAdapter(Context context, HandleAssessmentClick clickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        this.context = context;
    }

    public AssessmentAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView title;
        private final TextView type;
        private final TextView dueDate;
        private final TextView courseTitle;

        private final Button deleteAssessmentButton;
        private final Button editAssessmentButton;

        HandleAssessmentClick clickListener;


        public AssessmentViewHolder(@NonNull View itemView, HandleAssessmentClick clickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            dueDate = itemView.findViewById(R.id.due_date);
            courseTitle = itemView.findViewById(R.id.course_title);

            deleteAssessmentButton = itemView.findViewById(R.id.delete_assessment);
            editAssessmentButton = itemView.findViewById(R.id.edit_assessment);

            this.clickListener = clickListener;

            itemView.setOnClickListener((v) -> {
                int position = getAbsoluteAdapterPosition();
                final Assessment current = mAssessments.get(position);
                Intent intent = new Intent(context, AssessmentDetailActivity.class);
                if (current.getCourseId() > 0) {
                    mCourses.forEach(course -> {
                        if (course.getId() == current.getCourseId()) {
                            intent.putExtra("title", current.getTitle());
                            intent.putExtra("type", current.getType());
                            intent.putExtra("position", position);
                            intent.putExtra("courseId", current.getCourseId());
                            intent.putExtra("courseTitle", course.getTitle());
                            intent.putExtra("id", current.getId());
                            intent.putExtra("assessmentDate", current.getEndDate());
                        }
                    });
                } else {
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("type", current.getType());
                    intent.putExtra("position",position);
                    intent.putExtra("courseId", current.getCourseId());
                    intent.putExtra("courseTitle", "No courses added");
                    intent.putExtra("id",current.getId());
                    intent.putExtra("assessmentDate", current.getEndDate());
                }
                context.startActivity(intent);
            });
        }

        @Override
        public void onClick(View v) {
            clickListener.onAssessmentClick(getAbsoluteAdapterPosition());
        }
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessment current = mAssessments.get(position);
            // TODO the below doesnt work because mCourses.get is looking for an index
            // TODO HOW DO I GET THE CURRENT COURSE MATCHING THE CURRENT COURSE ID FROM ASSESSMENTS
//            Course currentCourse = mCourses.get(current.getCourseId());

            // TODO The below stops the program I am trying to add
            // TODO add the course titles to the recycler view.
            // TODO I kinda got it working where it was adding the wrong course title.
            /**
             * SETS THE TEXT VALUES FROM THE CURRENT COURSE AND
             * COURSES TITLE IF ITS BEEN ADDED.
             *
             *
             * */
                mAssessments.forEach(assessment -> {
                    if(assessment.getCourseId() > 0) {
                        mCourses.forEach((course) -> {
                            // TODO HELP HELP HELP
                            // TODO FOR SOME REASON ANYTHING IN THE LIST ISNT VALID
                            if (course.getId() == assessment.getCourseId()){
                                holder.title.setText(assessment.getTitle());
                                holder.type.setText(assessment.getType());
                                holder.dueDate.setText(assessment.getEndDate());
                                holder.courseTitle.setText(course.getTitle());
                            }
                        });
                    } else if (assessment.getCourseId() == 0) {
                        holder.title.setText(current.getTitle());
                        holder.type.setText(current.getType());
                        holder.dueDate.setText(current.getEndDate());
                        holder.courseTitle.setText("No Courses Added");
                    }
                });


        }
//        else {
//            holder.title.setText("No title");
////            holder.courseTitle.setText("No Courses Added");
//            holder.type.setText("No Assessment Type Selected");
//            holder.dueDate.setText("No Date");
//        }

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

    public void setWords(List<Assessment> assessments, List<Course> courses) {
        mCourses = courses;
        mAssessments = assessments;
        notifyDataSetChanged();
    }

    public void setWords(List<Assessment> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    public interface HandleAssessmentClick {
        void onAssessmentClick(int position);
        void removeAssessments(Assessment assessment);
        void editAssessments(Assessment assessment);
    }

    public class AssessmentCourse () {

    }
}
