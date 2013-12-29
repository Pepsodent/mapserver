package fi.paivola.mapserver.models;

import fi.paivola.mapserver.core.DataFrame;
import fi.paivola.mapserver.core.Event;
import fi.paivola.mapserver.core.GameManager;
import fi.paivola.mapserver.core.GlobalModel;
import fi.paivola.mapserver.core.setting.SettingMaster;
import static java.lang.Integer.parseInt;

public class AsdGlobal extends GlobalModel {

    public AsdGlobal(int id, SettingMaster sm) {
        super(id, sm);
    }
    
    public AsdGlobal() {
        super();
    }

    @Override
    public void onTick(DataFrame last, DataFrame current) {
        current.saveGlobalData("asdness", ""+(parseInt(last.getGlobalData("asdness"))+ 1));
    }

    @Override
    public void onEvent(Event e) {

    }

    @Override
    public void onRegisteration(GameManager gm, SettingMaster sm) {

    }

    @Override
    public void onGenerateDefaults(DataFrame df) {
        df.saveGlobalData("asdness", "1");
    }

}