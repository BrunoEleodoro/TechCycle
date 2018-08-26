package me.brunoeleodoro.techcycle.select_points.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.brunoeleodoro.techcycle.R;
import me.brunoeleodoro.techcycle.select_points.SelectPointsView;
import me.brunoeleodoro.techcycle.select_points.models.Point;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.PointsHolder>{

    List<Point> points = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    SelectPointsView selectPointsView;

    public PointsAdapter(List<Point> points, Context context, SelectPointsView selectPointsView) {
        this.points = points;
        this.context = context;
        this.selectPointsView = selectPointsView;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PointsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PointsHolder(inflater.inflate(R.layout.linha_place, null));
    }

    @Override
    public void onBindViewHolder(@NonNull PointsHolder holder, int position) {
        final Point point = points.get(position);
        holder.txt_endereco.setText(point.getEndereco());
        holder.txt_sub.setText(point.getSub());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPointsView.click(point);

            }
        });
    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    class PointsHolder extends RecyclerView.ViewHolder
    {
        TextView txt_endereco;
        TextView txt_sub;
        public PointsHolder(@NonNull View itemView) {
            super(itemView);

            txt_endereco = itemView.findViewById(R.id.txt_endereco);
            txt_sub = itemView.findViewById(R.id.txt_sub);
        }
    }

}
