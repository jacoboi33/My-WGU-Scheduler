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

import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.Entity.Term;
import scheduler.wgu.mywguscheduler.R;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private final LayoutInflater mInflater;
    private List<Term> mTerms;
    private HandleTermClick clickListerner;
    private final Context context;

    public TermAdapter(Context context, HandleTermClick clickListener) {
        mInflater = LayoutInflater.from(context);
        this.clickListerner = clickListener;
        this.context = context;
    }

    class TermViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
//        private final TextView notifications;
        private final TextView startDate;
        private final TextView endDate;

        private final Button deleteTerm;
        private final Button editTerm;

        private TermViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.term_title);
//            notifications = itemView.findViewById(R.id.notifications_enabled);
            startDate = itemView.findViewById(R.id.start_date);
            endDate = itemView.findViewById(R.id.end_date);

            deleteTerm = itemView.findViewById(R.id.delete_term);
            editTerm = itemView.findViewById(R.id.edit_term);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAbsoluteAdapterPosition();
//                    final Term current = mTerms.get(position);
//                    Intent intent = new Intent(context, TermDetailActivity.class);
//                    intent.putExtra("title", current.getTermTitle());
////                    intent.putExtra("notification", notifications.toString());
//                    intent.putExtra("start date", current.getStartDate());
//                    intent.putExtra("end date", current.getEndDate());
//                    context.startActivity(intent);
//                }
//            });

        }
    }


    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        if (mTerms != null) {
            Term current = mTerms.get(position);
            holder.title.setText(current.getTermTitle());
            holder.startDate.setText(current.getStartDate());
            holder.endDate.setText(current.getEndDate());
        }
        else {
            holder.title.setText("No Terms");
            holder.startDate.setText("No start date");
            holder.endDate.setText("No end date");
        }

        holder.deleteTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListerner.removeTerm(mTerms.get(position));
            }
        });

        holder.editTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListerner.editTerm(mTerms.get(position));
            }
        });

    }

    public void setWords(List<Term> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTerms != null)
            return mTerms.size();
        else
            return 0;
    }

    public interface HandleTermClick {
        void removeTerm(Term term);
        void editTerm(Term term);
    }
}
