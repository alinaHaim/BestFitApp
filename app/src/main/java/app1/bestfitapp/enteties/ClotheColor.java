package app1.bestfitapp.enteties;

/**
 * Created by user on 08/03/2018.
 */

public class ClotheColor {
    private final int NEAR_VALUE = 40;
    private final int MAX_BLACK_VALUE = 180;
    private final int MAX_WHITE_VALUE = 100;
    private final int MAX_DARK_BLUE_VALUE = 300;
    private final float BLUE_FACTOR = 1.5f;

    enum eLevel {Black, Gray, White, DarkBlue, Colored}

   public int r, g, b;
    public eLevel level;

    public ClotheColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;

        level = getLevel();
    }

    public eLevel getLevel() {
        boolean isNearColor = ((Math.abs(r - g) < NEAR_VALUE) &&
                (Math.abs(r - b) < NEAR_VALUE) &&
                (Math.abs(g - b) < NEAR_VALUE));

        if (isNearColor) {
            if (r + b + g <= MAX_BLACK_VALUE) {
                return eLevel.Black;
            }
            if (r + b + g >= (255 * 3) - MAX_WHITE_VALUE) {
                return eLevel.White;
            }

            return eLevel.Gray;
        }

        if ((b >= r * BLUE_FACTOR) && (b >= g * BLUE_FACTOR)) {
            if (r + g + b <= MAX_DARK_BLUE_VALUE) {
                return eLevel.DarkBlue;
            }
        }

        return eLevel.Colored;
    }
}
