package packs.Logic.TagLib;

/**
 * Created by ASUS on 25.12.2016.
 */


import java.util.ArrayList;

/**
 * Класс, где костыльно создается БД с условной связью "многие-ко-монгим"
 */
public class TagMap {
    static ArrayList<Tag> tagMap;

    public TagMap() {
        tagMap = new ArrayList<>();

        tagMap.add(new Tag(
                "romantic",
                new String[]{"cafe","coffee","park","bridge"},
                new String[]{"romance","speed-dating"}
        ));

        tagMap.add(new Tag(
                "brokenHeart",
                new String[]{"bakeries","gallery"},
                new String[]{"speed-dating","party","yoga","cinema"}
        ));

        tagMap.add(new Tag(
                "sad",
                new String[]{"bridge","park","roof","bar","pub","fastfood"},
                new String[]{"concert"}
        ));

        tagMap.add(new Tag(
                "heat",
                new String[]{"beaches","park","lakes","fountain","water-park","zoo"},
                new String[]{"festival","sport"}
        ));

        tagMap.add(new Tag(
                "student",
                new String[]{"academy-of-music","course","education-centers","painting","library"},
                new String[]{"education","presentation"}
        ));

        tagMap.add(new Tag(
                "cheer",
                new String[]{"cinema","paintball","climbing-walls","interesting-places","paintball","karts","wind-tunnels","slope","stable","rollerdromes","bowling","billiards"},
                new String[]{"flashmob","night","party","cinema","holiday","festival","concert"}
        ));

        tagMap.add(new Tag(
                "single",
                new String[]{"bar","pub","fitness","bowling","library","metro"},
                new String[]{"speed-dating","party","yoga","festival","fashion","concert","dance-trainings"}
        ));

        tagMap.add(new Tag(
                "pi",
                new String[]{"strip-club","clubs","bar","pub"},
                new String[]{"party"}
        ));

        tagMap.add(new Tag(
                "balance",
                new String[]{"church", "bridge", "library", "swimming-pool", "temple"},
                new String[]{"yoga"}
        ));

        tagMap.add(new Tag(
                "night",
                new String[]{"clubs","observatory"},
                new String[]{"night","party"}
        ));

        tagMap.add(new Tag(
                "friends",
                new String[]{"cinema","paintball","climbing-walls","interesting-places","paintball","karts","wind-tunnels","slope","stable","rollerdromes","bowling","billiards"},
                new String[]{"flashmob","night","party","cinema","holiday","festival"}
        ));

        tagMap.add(new Tag(
                "kid",
                new String[]{"kids"},
                new String[]{"kids","festival"}
        ));

        tagMap.add(new Tag(
                "family",
                new String[]{"kids","circus","cinema","paintball","climbing-walls"},
                new String[]{"kids","tour","cinema","magic","circus"}
        ));

        tagMap.add(new Tag(
                "roundClock",
                new String[]{"bar","pub","car-washes","airports"},
                new String[]{"night","party","festival"}
        ));

        tagMap.add(new Tag(
                "snow",
                new String[]{"ice-rink","slope","cafe","coffee"},
                new String[]{"festival","holiday","sport"}
        ));

        tagMap.add(new Tag(
                "bound",
                new String[]{"interesting-places","paintball","karts","wind-tunnels","slope","stable","rollerdromes"},
                new String[]{"flashmob","magic","holiday","festival"}
        ));

        tagMap.add(new Tag(
                "eat",
                new String[]{"cafe","coffee","bakeries","health-food","vegetarian","roof","restaurants","banquets","canteens"},
                new String[]{"other"}
        ));

        tagMap.add(new Tag(
                "movie",
                new String[]{"cinema"},
                new String[]{"cinema"}
        ));

        tagMap.add(new Tag(
                "music",
                new String[]{"concert-hall","bar-s-zhivoj-muzykoj","karaoke"},
                new String[]{"concert"}
        ));

        tagMap.add(new Tag(
                "home",
                new String[]{"vintage","cafe","coffee"},
                new String[]{"other"}
        ));

        tagMap.add(new Tag(
                "nature",
                new String[]{"lakes","park","diving", "cats", "dogs", "animal-shelters"},
                new String[]{"yoga","sport","photo","tour"}
        ));

        tagMap.add(new Tag(
                "tired",
                new String[]{""},
                new String[]{""}
        ));

        tagMap.add(new Tag(
                "expensive",
                new String[]{"restaurants","show-room","strip-club","vintage"},
                new String[]{"social-activity","ball","fashion","show"}
        ));

        tagMap.add(new Tag(
                "creditCard",
                new String[]{"restaurants","show-room","books","vintage","cafe","salons","karaoke"},
                new String[]{"social-activity","ball","fashion","show","party"}
        ));

        tagMap.add(new Tag(
                "rest",
                new String[]{"fountain","lakes","park","yard","coffee"},
                new String[]{"yoga","evening","games","quest"}
        ));

        tagMap.add(new Tag(
                "rain",
                new String[]{"theatre","coffee","books","library","anti-cafe","art-centers"},
                new String[]{"cinema","circus","stand-up","quest"}
        ));

        tagMap.add(new Tag(
                "work",
                new String[]{"coworking","library","business"},
                new String[]{"business-events","meeting"}
        ));

        tagMap.add(new Tag(
                "season",
                new String[]{"strip-club", "ice-rink", "gifts", "kids", "slope"},
                new String[]{"holiday", "kids", "festival", "yarmarki-razvlecheniya-yarmarki"}
        ));

        tagMap.add(new Tag(
                "drink",
                new String[]{"pub", "bar","bar-s-zhivoj-muzykoj", "gay-bar", "restaurants","brewery","clubs"},
                new String[]{"party"}
        ));

        tagMap.add(new Tag(
                "meditation",
                new String[]{"church", "bridge", "library", "swimming-pool", "temple"},
                new String[]{"yoga"}
        ));
    }

    private Tag findTag(String tagType){
        for(int i = 0; i < tagMap.size(); i++) {
            Tag currentTag = tagMap.get(i);
            if (currentTag.getMood() == tagType)

                return currentTag;
        }

        return null;
    }

    public ArrayList<String> getPlaceTagsToSearch(ArrayList<String> moods){
        ArrayList<String> tags = new ArrayList<>();
        for(int i = 0; i < moods.size(); i++){
            String mood = moods.get(i);
            Tag tagForMood = findTag(mood);
            String[] places = tagForMood.getPlaces();
            for(int j = 0; j < places.length; j++)
                if(!tags.contains(places[j]))
                    tags.add(places[j]);
        }
        return tags;
    }

    public ArrayList<String> getEventTagsToSearch(ArrayList<String> moods){
        ArrayList<String> tags = new ArrayList<>();
        for(int i = 0; i < moods.size(); i++){
            String mood = moods.get(i);
            Tag tagForMood = findTag(mood);
            String[] places = tagForMood.getEvents();
            for(int j = 0; j < places.length; j++)
                if(!tags.contains(places[j]))
                    tags.add(places[j]);
        }
        return tags;
    }
}
