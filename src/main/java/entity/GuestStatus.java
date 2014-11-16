package entity;

/**
 * Created by nectarius on 10.05.14.
 */
public enum GuestStatus {

    PROMISED,
    NOT_YET_DECIDED,
    UNLIKELY,
    REMOVED;

    @Override
    public String toString() {
        String value = super.toString();
        if(value.equals("PROMISED")){
            return "Видимо да";
        }

        if(value.equals("NOT_YET_DECIDED")){
            return "Не определился";
        }

        if(value.equals("UNLIKELY")){
            return "Маловероятно";
        }

        return "Неизвестно";
    }
}
