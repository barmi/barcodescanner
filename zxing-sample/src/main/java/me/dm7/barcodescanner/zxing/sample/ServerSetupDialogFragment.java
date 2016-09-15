package me.dm7.barcodescanner.zxing.sample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

public class ServerSetupDialogFragment extends DialogFragment {
    public interface ServerSetupDialogListener {
        //public void onCameraSelected(int cameraId);
        public void setServerAddress(String serverAddress);
    }

    //private int mCameraId;
    private String mServerAddress;
    private ServerSetupDialogListener mListener;

    public void onCreate(Bundle state) {
        super.onCreate(state);
        setRetainInstance(true);
    }

    public static ServerSetupDialogFragment newInstance(ServerSetupDialogListener listener, String serverAddress) {
        ServerSetupDialogFragment fragment = new ServerSetupDialogFragment();
        //fragment.mCameraId = cameraId;
        fragment.mServerAddress = serverAddress;
        fragment.mListener = listener;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // ref : http://stackoverflow.com/questions/16407722
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View tranactionLayout = View.inflate(getActivity(), R.layout.serversetupfragment, null);
        final EditText text_addr = (EditText)tranactionLayout.findViewById(R.id.edittext_addr);

        builder.setView(tranactionLayout)
        // Set the dialog title
                .setTitle(R.string.server_setup)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                // Set the action buttons
                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedIndices results somewhere
                        // or return them to the component that opened the dialog
                        if (mListener != null) {
                            mListener.setServerAddress(text_addr.getText().toString());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        text_addr.setText(mServerAddress);
        return builder.create();
    }
}
