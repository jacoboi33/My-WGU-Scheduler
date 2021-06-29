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

//    private ScheduleManagementRepository repository;
    private final LayoutInflater mInflater;
    private List<Assessment> mAssessments;
    private List<Course> mCourses;
//    private final List<Course> mCourseTitle;
    private HandleAssessmentClick clickListener;
    private final Context context;

    public AssessmentAdapter(Context context, HandleAssessmentClick clickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        this.context = context;
    }

    public AssessmentAdapter(Context context) {
//        mCourseTitle = repository.getCourseTitle();
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

//        private AssessmentViewholder(View itemView) {
//            super(itemView);
//
//        }


        public AssessmentViewHolder(@NonNull View itemView, HandleAssessmentClick clickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            dueDate = itemView.findViewById(R.id.due_date);
            courseTitle = itemView.findViewById(R.id.course_title);

            deleteAssessmentButton = itemView.findViewById(R.id.delete_assessment);
            editAssessmentButton = itemView.findViewById(R.id.edit_assessment);

            this.clickListener = clickListener;



//            itemView.setOnClickListener(this);

            itemView.setOnClickListener((v) -> {
                int position = getAbsoluteAdapterPosition();
                final Assessment current = mAssessments.get(position);
                final Course currentCourse = mCourses.get(current.getCourseId());
                Intent intent = new Intent(context, AssessmentDetailActivity.class);
                intent.putExtra("title", current.getTitle());
                intent.putExtra("type", current.getType());
                intent.putExtra("position",position);
                intent.putExtra("courseId", current.getCourseId());
//                intent.putExtra("courseTitle", current.getCourseTitle());
                intent.putExtra("id",current.getId());
                intent.putExtra("assessmentDate", current.getEndDate());
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
//        AssessmentViewModel viewModel = new AssessmentViewModel(AssessmentActivity.class);
//        ScheduleManagementRepository repository = new ScheduleManagementRepository(context, );
        if (mAssessments != null) {
            Assessment current = mAssessments.get(position);
            // HELP HELP HELP HELP
            // TODO The below stops the program I am trying to add
            // TODO add the course titles to the recycler view.
            // TODO I kinda got it working where it was adding the wrong course title.
            if(current.getCourseId() > 0) {

                mCourses.forEach((course) -> {
                    if (course.getId() == current.getCourseId()){
                        holder.title.setText(current.getTitle());
                        holder.type.setText(current.getType());
                        holder.dueDate.setText(current.getEndDate());
                        holder.courseTitle.setText(course.getTitle());
                    }
//                        Log.i("COURSEID ", " COURSEID " + course.getId());
//               course.getId() ==
                });
            } else {
                holder.title.setText(current.getTitle());
                holder.type.setText(current.getType());
                holder.dueDate.setText(current.getEndDate());
                holder.courseTitle.setText("No Courses Added");
            }



//            Comparator<Course> c = new Comparator<Course>() {
//                public int compare(Course u1, Course u2)
//                {
//                    return u1.getId().compareTo(u2.getId());
//                }
//            };

//            Course currentCourse;

//            if (current.getCourseId() > 0) {
//                mCourses.;
//                int index = mCourses.indexOf(0, current.getCourseId());
//                int index = Collections.binarySearch(
//                        mCourses,
//                        new Course(
//                                current.getCourseId(),
//                                0,
//                                0,
//                                null,
//                                null,
//                                null,
//                                null,
//                                null),
//                        Collections.reverseOrder());
//
//                Course currentCourse = mCourses.get(index);
//                holder.courseTitle.setText(currentCourse.getTitle());
////                mCourses.
////                for (Course course: mCourses) {
////                    if(current.getCourseId() == course.getId())
////                        currentCourse = course;
////                }
////                currentCourse = mCourses.get(current.getCourseId());
//            } else {
//                holder.courseTitle.setText("No Courses Added");
////                currentCourse = new Course();
//            }

//            if (current.getCourseId() == )

//            holder.title.setText(current.getTitle());
//            holder.type.setText(current.getType());
//            holder.dueDate.setText(current.getEndDate());
//            holder.courseTitle.setText(current.getCourseId() > 0 ? mCourses.get(current.getCourseId()).getTitle() : "No Courses Added");
        } else {
            holder.title.setText("No title");
//            holder.courseTitle.setText("No Courses Added");
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
}
