package ru.ok.technopolis.students;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private final List<Student> students;
    private final Listener onStudentClickListener;
    private int focus = -1;

    public StudentAdapter(List<Student> students, Listener onStudentClickListener) {
        this.students = students;
        this.onStudentClickListener = onStudentClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_item, viewGroup, false);
        view.setOnClickListener(v -> {
            focus = ((RecyclerView.LayoutParams) v.getLayoutParams()).getViewLayoutPosition();
            notifyDataSetChanged();
            onStudentClickListener.onStudentClick((Student) v.getTag());
        });
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder viewHolder, final int i) {
        Student student = students.get(i);
        viewHolder.bind(student);
        viewHolder.itemView.setTag(student);
        if (focus == i) {
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    public void setFocus(int i) {
        focus = i;
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static final class StudentViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final TextView surnameTextView;
        private final ImageView photoImageView;

        private StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.student_item__name);
            surnameTextView = itemView.findViewById(R.id.student_item__surname);
            photoImageView = itemView.findViewById(R.id.student_item__image);
        }

        private void bind(@NonNull Student student) {
            nameTextView.setText(student.getFirstName());
            surnameTextView.setText(student.getSecondName());
            photoImageView.setImageResource(student.getPhoto());
        }
    }

}

interface Listener {
    void onStudentClick(Student student);
}