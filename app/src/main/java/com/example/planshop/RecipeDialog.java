package com.example.planshop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class RecipeDialog extends AppCompatDialogFragment {
    private EditText editTextRecipe;
    private RecipeDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog2, null);

        builder.setView(view).setTitle("Add recipe")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String recipeName = editTextRecipe.getText().toString().trim();
                        if(!recipeName.isEmpty()){
                            listener.applyTexts(recipeName);
                        }

                    }
                });

        editTextRecipe = view.findViewById(R.id.edit_add_recipe);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (RecipeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement RecipeDialogListener");
        }
    }

    public interface RecipeDialogListener{
        void applyTexts(String recieName);
    }
}
