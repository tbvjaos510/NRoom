public class NMapPOIflagType
{

    public static int UNKNOWN = 0x0000;

    // Single POI icons
    private static int SINGLE_POI_BASE = 0x0100;

    // Spot, Pin icons
    public static int SPOT = SINGLE_POI_BASE + 1;
    public static int PIN = SPOT + 1;

    // Direction POI icons: From, To
    private static int DIRECTION_POI_BASE = 0x0200;
    public static int FROM = DIRECTION_POI_BASE + 1;
    public static int TO = FROM + 1;

    // end of single marker icon
    public static int SINGLE_MARKER_END = 0x04FF;

    // Direction Number icons
    private static int MAX_NUMBER_COUNT = 1000;
    public static int NUMBER_BASE = 0x1000; // set NUMBER_BASE + 1 for '1' number
    public static int NUMBER_END = NUMBER_BASE + MAX_NUMBER_COUNT;

    // Custom POI icons
    private static int MAX_CUSTOM_COUNT = 1000;
    public static int CUSTOM_BASE = NUMBER_END;
    public static int CUSTOM_END = CUSTOM_BASE + MAX_CUSTOM_COUNT;

    // Clickable callout에 보여지는 화살표
    public static int CLICKABLE_ARROW = CUSTOM_END + 1;

    public static bool isBoundsCentered(int markerId)
    {
        bool boundsCentered = false;

        switch (markerId)
        {
            default:
                if (markerId >= NMapPOIflagType.NUMBER_BASE && markerId < NMapPOIflagType.NUMBER_END)
                {
                    boundsCentered = true;
                }
                break;
        }

        return boundsCentered;
    }

    public static int getMarkerId(int poiFlagType, int iconIndex)
    {
        int markerId = poiFlagType + iconIndex;

        return markerId;
    }

    public static int getPOIflagType(int markerId)
    {
        int poiFlagType = UNKNOWN;

        // Alphabet POI icons
        if (markerId >= NUMBER_BASE && markerId < NUMBER_END)
        { // Direction Number icons
            poiFlagType = NUMBER_BASE;
        }
        else if (markerId >= CUSTOM_BASE && markerId < CUSTOM_END)
        { // Custom POI icons
            poiFlagType = CUSTOM_BASE;
        }
        else if (markerId > SINGLE_POI_BASE)
        {
            poiFlagType = markerId;
        }

        return poiFlagType;
    }

    public static int getPOIflagIconIndex(int markerId)
    {
        int iconIndex = 0;

        if (markerId >= NUMBER_BASE && markerId < NUMBER_END)
        { // Direction Number icons
            iconIndex = markerId - (NUMBER_BASE + 1);
        }
        else if (markerId >= CUSTOM_BASE && markerId < CUSTOM_END)
        { // Custom POI icons
            iconIndex = markerId - (CUSTOM_BASE + 1);
        }
        else if (markerId > SINGLE_POI_BASE)
        {
            iconIndex = 0;
        }

        return iconIndex;
    }
}
