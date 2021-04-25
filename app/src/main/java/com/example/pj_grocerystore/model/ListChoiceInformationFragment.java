package com.example.pj_grocerystore.model;

import com.example.pj_grocerystore.R;

import java.util.ArrayList;

public class ListChoiceInformationFragment {
    public static ArrayList<ChoiceOfInformation> getListChoice() {
        ArrayList<ChoiceOfInformation> list = new ArrayList<>();
        list.add(new ChoiceOfInformation(R.drawable.ic_logout,"LogOut","Help you log out"));
        list.add(new ChoiceOfInformation(R.drawable.ic_information,"Introduce","Introduce about app"));
        list.add(new ChoiceOfInformation(R.drawable.ic_information,"History","Show history buy sell of you"));
        return list;
    }
}
