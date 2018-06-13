package getset.guidance.career.getset;

import getset.guidance.career.getset.SportCardModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SportCardsUtils {

    public static Collection<SportCardModel> generateSportCards() {
        List<SportCardModel> sportCardModels = new ArrayList<>(4);

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Apptitude")
                    .withImageResId(R.drawable.aptitude)
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
                    .withImageResId(R.drawable.aptitude)
                    .withBackgroundColorResId(R.color.usc_gold)
                    .build());

        }

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Intrest")
                    .withImageResId(R)
                    .withBackgroundColorResId(R.color.portland_orange)
                    .build());

        }
        return sportCardModels;
    }
}
