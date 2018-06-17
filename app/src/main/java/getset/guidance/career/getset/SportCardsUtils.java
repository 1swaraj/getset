package getset.guidance.career.getset;

import getset.guidance.career.getset.SportCardModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SportCardsUtils {
    public static List<SportCardModel> sportCardModels;
    public static void generateSportCards() {
        sportCardModels= new ArrayList<>(4);
        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Aptitude")
                    .withImageResId(R.drawable.abc)
                    .withBackgroundColorResId(R.color.brown)
                    .build());

        }

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Personality")
                    .withSportSubtitle("")
                    .withSportRound("")
                    .withImageResId(R.drawable.index)
                    .withBackgroundColorResId(R.color.blue)
                    .build());

        }

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Study Habits")
                    .withImageResId(R.drawable.study)
                    .withBackgroundColorResId(R.color.usc_gold)
                    .build());

        }

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Intrest")
                    .withImageResId(R.drawable.images)
                    .withBackgroundColorResId(R.color.portland_orange)
                    .build());

        }
    }
}
