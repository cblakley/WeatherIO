package net.learn2develop.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import learn2develop.Fragments.R;
public class Fragment1 extends Fragment {
    ListView listView;
@Override
    public View onCreateView(LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {

    //---Inflate the layout for this fragment---
        // Let's change the code below to get a View
    //return inflater.inflate(R.layout.fragment1, container, false);

   // Use getView(). This will return the root view for the fragment, with this you can call findViewById().

    View view = inflater.inflate(R.layout.fragment1, container, false);

    listView = (ListView) view.findViewById(R.id.list);

    // Defined Array values to show in ListView
    String[] values = new String[] { "Android List View",
            "Adapter implementation",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Android Example List View"
    };

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
            android.R.layout.simple_list_item_1, android.R.id.text1, values);


    // Assign adapter to ListView
    listView.setAdapter(adapter);

    // ListView Item Click Listener
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            // TODO Auto-generated method stub
            // ListView Clicked item index
            int itemPosition     = position;

            // ListView Clicked item value
            String  itemValue    = (String) listView.getItemAtPosition(position);

            // Show Alert
            Toast.makeText(getActivity(),
                    "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                    .show();

        }

    });

    return view;
    }

    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //The Fragment will be attached to a null activity until onActivityCreated...
        // that is, if you call getActivity() in onCreate or onCreateView,
        // it will return null because the Activity hasn't been created yet.
        // So make sure you have all of your calls to getActivity() in or after
        // onActivityCreated
        TextView lbl = (TextView) getActivity().findViewById(R.id.tv1);
        Toast.makeText(getActivity(), lbl.getText(),
                Toast.LENGTH_LONG).show();

    }
}
