package scheduler.wgu.mywguscheduler.UI;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import scheduler.wgu.mywguscheduler.R;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorViewHolder> {

    class InstructorViewHolder extends RecyclerView.ViewHolder {
        private final TextView instructorItemView;


        private InstructorViewHolder(View itemView) {
            super(itemView);
            instructorItemView = itemView.findViewById(R.id.);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final ProductEntity current = mProducts.get(position);
                    Intent intent = new Intent(context, PartActivity.class);
                    intent.putExtra("productName", current.getProductName());
                    intent.putExtra("productPrice", Double.toString(current.getProductPrice()));
                    intent.putExtra("productID", current.getProductID());
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public InstructorAdapter.InstructorViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull InstructorAdapter.InstructorViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
