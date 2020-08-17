package ro.jademy.carrental.services;


import java.util.ArrayList;
import java.util.List;

public class ExtraOptionsService {
    List<ExtraOption> extraOptionList = new ArrayList<>();


    public void initAllExtras() {
        extraOptionList.add(new ExtraOption("Baby-Seat", 30, false));
        extraOptionList.add(new ExtraOption("Baby-MegaSeat", 50, false));
        extraOptionList.add(new ExtraOption("Baby-UberSeat", 80, false));
    }
}
