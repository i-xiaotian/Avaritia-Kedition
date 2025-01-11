package morph.avaritia.the_north.capability.api;

import java.text.SimpleDateFormat;

public interface IInfinitySwordCooldown {

    void setCooldown(long millis);

    long getCooldown();

    void tick();

    default String cooldownString() {
        SimpleDateFormat sdf = getDateFormat();
        return sdf.format(getCooldown());
    }

    default SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
}
