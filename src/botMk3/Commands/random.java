package botMk3.Commands;

import java.util.ArrayList;
import java.util.Random;

import botMk3.Interfaces.Command;
import botMk3.Lib;

public class random implements Command {
    private String name = "random";

    private String[] PokemonData = {
            "bulbasaur",
            "ivysaur",
            "venusaur",
            "charmander",
            "charmeleon",
            "charizard",
            "squirtle",
            "wartortle",
            "blastoise",
            "caterpie",
            "metapod",
            "butterfree",
            "weedle",
            "kakuna",
            "beedrill",
            "pidgey",
            "pidgeotto",
            "pidgeot",
            "rattata",
            "raticate",
            "spearow",
            "fearow",
            "ekans",
            "arbok",
            "pikachu",
            "raichu",
            "sandshrew",
            "sandslash",
            "nidoran-f",
            "nidorina",
            "nidoqueen",
            "nidoran-m",
            "nidorino",
            "nidoking",
            "clefairy",
            "clefable",
            "vulpix",
            "ninetales",
            "jigglypuff",
            "wigglytuff",
            "zubat",
            "golbat",
            "oddish",
            "gloom",
            "vileplume",
            "paras",
            "parasect",
            "venonat",
            "venomoth",
            "diglett",
            "dugtrio",
            "meowth",
            "persian",
            "psyduck",
            "golduck",
            "mankey",
            "primeape",
            "growlithe",
            "arcanine",
            "poliwag",
            "poliwhirl",
            "poliwrath",
            "abra",
            "kadabra",
            "alakazam",
            "machop",
            "machoke",
            "machamp",
            "bellsprout",
            "weepinbell",
            "victreebel",
            "tentacool",
            "tentacruel",
            "geodude",
            "graveler",
            "golem",
            "ponyta",
            "rapidash",
            "slowpoke",
            "slowbro",
            "magnemite",
            "magneton",
            "farfetchd",
            "doduo",
            "dodrio",
            "seel",
            "dewgong",
            "grimer",
            "muk",
            "shellder",
            "cloyster",
            "gastly",
            "haunter",
            "gengar",
            "onix",
            "drowzee",
            "hypno",
            "krabby",
            "kingler",
            "voltorb",
            "electrode",
            "exeggcute",
            "exeggutor",
            "cubone",
            "marowak",
            "hitmonlee",
            "hitmonchan",
            "lickitung",
            "koffing",
            "weezing",
            "rhyhorn",
            "rhydon",
            "chansey",
            "tangela",
            "kangaskhan",
            "horsea",
            "seadra",
            "goldeen",
            "seaking",
            "staryu",
            "starmie",
            "mr-mime",
            "scyther",
            "jynx",
            "electabuzz",
            "magmar",
            "pinsir",
            "tauros",
            "magikarp",
            "gyarados",
            "lapras",
            "ditto",
            "eevee",
            "vaporeon",
            "jolteon",
            "flareon",
            "porygon",
            "omanyte",
            "omastar",
            "kabuto",
            "kabutops",
            "aerodactyl",
            "snorlax",
            "articuno",
            "zapdos",
            "moltres",
            "dratini",
            "dragonair",
            "dragonite",
            "mewtwo",
            "mew",
            "chikorita",
            "bayleef",
            "meganium",
            "cyndaquil",
            "quilava",
            "typhlosion",
            "totodile",
            "croconaw",
            "feraligatr",
            "sentret",
            "furret",
            "hoothoot",
            "noctowl",
            "ledyba",
            "ledian",
            "spinarak",
            "ariados",
            "crobat",
            "chinchou",
            "lanturn",
            "pichu",
            "cleffa",
            "igglybuff",
            "togepi",
            "togetic",
            "natu",
            "xatu",
            "mareep",
            "flaaffy",
            "ampharos",
            "bellossom",
            "marill",
            "azumarill",
            "sudowoodo",
            "politoed",
            "hoppip",
            "skiploom",
            "jumpluff",
            "aipom",
            "sunkern",
            "sunflora",
            "yanma",
            "wooper",
            "quagsire",
            "espeon",
            "umbreon",
            "murkrow",
            "slowking",
            "misdreavus",
            "unown",
            "wobbuffet",
            "girafarig",
            "pineco",
            "forretress",
            "dunsparce",
            "gligar",
            "steelix",
            "snubbull",
            "granbull",
            "qwilfish",
            "scizor",
            "shuckle",
            "heracross",
            "sneasel",
            "teddiursa",
            "ursaring",
            "slugma",
            "magcargo",
            "swinub",
            "piloswine",
            "corsola",
            "remoraid",
            "octillery",
            "delibird",
            "mantine",
            "skarmory",
            "houndour",
            "houndoom",
            "kingdra",
            "phanpy",
            "donphan",
            "porygon2",
            "stantler",
            "smeargle",
            "tyrogue",
            "hitmontop",
            "smoochum",
            "elekid",
            "magby",
            "miltank",
            "blissey",
            "raikou",
            "entei",
            "suicune",
            "larvitar",
            "pupitar",
            "tyranitar",
            "lugia",
            "ho-oh",
            "celebi",
            "treecko",
            "grovyle",
            "sceptile",
            "torchic",
            "combusken",
            "blaziken",
            "mudkip",
            "marshtomp",
            "swampert",
            "poochyena",
            "mightyena",
            "zigzagoon",
            "linoone",
            "wurmple",
            "silcoon",
            "beautifly",
            "cascoon",
            "dustox",
            "lotad",
            "lombre",
            "ludicolo",
            "seedot",
            "nuzleaf",
            "shiftry",
            "taillow",
            "swellow",
            "wingull",
            "pelipper",
            "ralts",
            "kirlia",
            "gardevoir",
            "surskit",
            "masquerain",
            "shroomish",
            "breloom",
            "slakoth",
            "vigoroth",
            "slaking",
            "nincada",
            "ninjask",
            "shedinja",
            "whismur",
            "loudred",
            "exploud",
            "makuhita",
            "hariyama",
            "azurill",
            "nosepass",
            "skitty",
            "delcatty",
            "sableye",
            "mawile",
            "aron",
            "lairon",
            "aggron",
            "meditite",
            "medicham",
            "electrike",
            "manectric",
            "plusle",
            "minun",
            "volbeat",
            "illumise",
            "roselia",
            "gulpin",
            "swalot",
            "carvanha",
            "sharpedo",
            "wailmer",
            "wailord",
            "numel",
            "camerupt",
            "torkoal",
            "spoink",
            "grumpig",
            "spinda",
            "trapinch",
            "vibrava",
            "flygon",
            "cacnea",
            "cacturne",
            "swablu",
            "altaria",
            "zangoose",
            "seviper",
            "lunatone",
            "solrock",
            "barboach",
            "whiscash",
            "corphish",
            "crawdaunt",
            "baltoy",
            "claydol",
            "lileep",
            "cradily",
            "anorith",
            "armaldo",
            "feebas",
            "milotic",
            "castform",
            "kecleon",
            "shuppet",
            "banette",
            "duskull",
            "dusclops",
            "tropius",
            "chimecho",
            "absol",
            "wynaut",
            "snorunt",
            "glalie",
            "spheal",
            "sealeo",
            "walrein",
            "clamperl",
            "huntail",
            "gorebyss",
            "relicanth",
            "luvdisc",
            "bagon",
            "shelgon",
            "salamence",
            "beldum",
            "metang",
            "metagross",
            "regirock",
            "regice",
            "registeel",
            "latias",
            "latios",
            "kyogre",
            "groudon",
            "rayquaza",
            "jirachi",
            "deoxys-normal",
            "turtwig",
            "grotle",
            "torterra",
            "chimchar",
            "monferno",
            "infernape",
            "piplup",
            "prinplup",
            "empoleon",
            "starly",
            "staravia",
            "staraptor",
            "bidoof",
            "bibarel",
            "kricketot",
            "kricketune",
            "shinx",
            "luxio",
            "luxray",
            "budew",
            "roserade",
            "cranidos",
            "rampardos",
            "shieldon",
            "bastiodon",
            "burmy",
            "wormadam-plant",
            "mothim",
            "combee",
            "vespiquen",
            "pachirisu",
            "buizel",
            "floatzel",
            "cherubi",
            "cherrim",
            "shellos",
            "gastrodon",
            "ambipom",
            "drifloon",
            "drifblim",
            "buneary",
            "lopunny",
            "mismagius",
            "honchkrow",
            "glameow",
            "purugly",
            "chingling",
            "stunky",
            "skuntank",
            "bronzor",
            "bronzong",
            "bonsly",
            "mime-jr",
            "happiny",
            "chatot",
            "spiritomb",
            "gible",
            "gabite",
            "garchomp",
            "munchlax",
            "riolu",
            "lucario",
            "hippopotas",
            "hippowdon",
            "skorupi",
            "drapion",
            "croagunk",
            "toxicroak",
            "carnivine",
            "finneon",
            "lumineon",
            "mantyke",
            "snover",
            "abomasnow",
            "weavile",
            "magnezone",
            "lickilicky",
            "rhyperior",
            "tangrowth",
            "electivire",
            "magmortar",
            "togekiss",
            "yanmega",
            "leafeon",
            "glaceon",
            "gliscor",
            "mamoswine",
            "porygon-z",
            "gallade",
            "probopass",
            "dusknoir",
            "froslass",
            "rotom",
            "uxie",
            "mesprit",
            "azelf",
            "dialga",
            "palkia",
            "heatran",
            "regigigas",
            "giratina-altered",
            "cresselia",
            "phione",
            "manaphy",
            "darkrai",
            "shaymin-land",
            "arceus",
            "victini",
            "snivy",
            "servine",
            "serperior",
            "tepig",
            "pignite",
            "emboar",
            "oshawott",
            "dewott",
            "samurott",
            "patrat",
            "watchog",
            "lillipup",
            "herdier",
            "stoutland",
            "purrloin",
            "liepard",
            "pansage",
            "simisage",
            "pansear",
            "simisear",
            "panpour",
            "simipour",
            "munna",
            "musharna",
            "pidove",
            "tranquill",
            "unfezant",
            "blitzle",
            "zebstrika",
            "roggenrola",
            "boldore",
            "gigalith",
            "woobat",
            "swoobat",
            "drilbur",
            "excadrill",
            "audino",
            "timburr",
            "gurdurr",
            "conkeldurr",
            "tympole",
            "palpitoad",
            "seismitoad",
            "throh",
            "sawk",
            "sewaddle",
            "swadloon",
            "leavanny",
            "venipede",
            "whirlipede",
            "scolipede",
            "cottonee",
            "whimsicott",
            "petilil",
            "lilligant",
            "basculin-red-striped",
            "sandile",
            "krokorok",
            "krookodile",
            "darumaka",
            "darmanitan-standard",
            "maractus",
            "dwebble",
            "crustle",
            "scraggy",
            "scrafty",
            "sigilyph",
            "yamask",
            "cofagrigus",
            "tirtouga",
            "carracosta",
            "archen",
            "archeops",
            "trubbish",
            "garbodor",
            "zorua",
            "zoroark",
            "minccino",
            "cinccino",
            "gothita",
            "gothorita",
            "gothitelle",
            "solosis",
            "duosion",
            "reuniclus",
            "ducklett",
            "swanna",
            "vanillite",
            "vanillish",
            "vanilluxe",
            "deerling",
            "sawsbuck",
            "emolga",
            "karrablast",
            "escavalier",
            "foongus",
            "amoonguss",
            "frillish",
            "jellicent",
            "alomomola",
            "joltik",
            "galvantula",
            "ferroseed",
            "ferrothorn",
            "klink",
            "klang",
            "klinklang",
            "tynamo",
            "eelektrik",
            "eelektross",
            "elgyem",
            "beheeyem",
            "litwick",
            "lampent",
            "chandelure",
            "axew",
            "fraxure",
            "haxorus",
            "cubchoo",
            "beartic",
            "cryogonal",
            "shelmet",
            "accelgor",
            "stunfisk",
            "mienfoo",
            "mienshao",
            "druddigon",
            "golett",
            "golurk",
            "pawniard",
            "bisharp",
            "bouffalant",
            "rufflet",
            "braviary",
            "vullaby",
            "mandibuzz",
            "heatmor",
            "durant",
            "deino",
            "zweilous",
            "hydreigon",
            "larvesta",
            "volcarona",
            "cobalion",
            "terrakion",
            "virizion",
            "tornadus-incarnate",
            "thundurus-incarnate",
            "reshiram",
            "zekrom",
            "landorus-incarnate",
            "kyurem",
            "keldeo-ordinary",
            "meloetta-aria",
            "genesect",
            "chespin",
            "quilladin",
            "chesnaught",
            "fennekin",
            "braixen",
            "delphox",
            "froakie",
            "frogadier",
            "greninja",
            "bunnelby",
            "diggersby",
            "fletchling",
            "fletchinder",
            "talonflame",
            "scatterbug",
            "spewpa",
            "vivillon",
            "litleo",
            "pyroar",
            "flabebe",
            "floette",
            "florges",
            "skiddo",
            "gogoat",
            "pancham",
            "pangoro",
            "furfrou",
            "espurr",
            "meowstic-male",
            "honedge",
            "doublade",
            "aegislash-shield",
            "spritzee",
            "aromatisse",
            "swirlix",
            "slurpuff",
            "inkay",
            "malamar",
            "binacle",
            "barbaracle",
            "skrelp",
            "dragalge",
            "clauncher",
            "clawitzer",
            "helioptile",
            "heliolisk",
            "tyrunt",
            "tyrantrum",
            "amaura",
            "aurorus",
            "sylveon",
            "hawlucha",
            "dedenne",
            "carbink",
            "goomy",
            "sliggoo",
            "goodra",
            "klefki",
            "phantump",
            "trevenant",
            "pumpkaboo-average",
            "gourgeist-average",
            "bergmite",
            "avalugg",
            "noibat",
            "noivern",
            "xerneas",
            "yveltal",
            "zygarde",
            "diancie",
            "hoopa",
            "volcanion",
            "deoxys-attack",
            "deoxys-defense",
            "deoxys-speed",
            "wormadam-sandy",
            "wormadam-trash",
            "shaymin-sky",
            "giratina-origin",
            "rotom-heat",
            "rotom-wash",
            "rotom-frost",
            "rotom-fan",
            "rotom-mow",
            "castform-sunny",
            "castform-rainy",
            "castform-snowy",
            "basculin-blue-striped",
            "darmanitan-zen",
            "meloetta-pirouette",
            "tornadus-therian",
            "thundurus-therian",
            "landorus-therian",
            "kyurem-black",
            "kyurem-white",
            "keldeo-resolute",
            "meowstic-female",
            "aegislash-blade",
            "pumpkaboo-small",
            "pumpkaboo-large",
            "pumpkaboo-super",
            "gourgeist-small",
            "gourgeist-large",
            "gourgeist-super",
            "venusaur-mega",
            "charizard-mega-x",
            "charizard-mega-y",
            "blastoise-mega",
            "alakazam-mega",
            "gengar-mega",
            "kangaskhan-mega",
            "pinsir-mega",
            "gyarados-mega",
            "aerodactyl-mega",
            "mewtwo-mega-x",
            "mewtwo-mega-y",
            "ampharos-mega",
            "scizor-mega",
            "heracross-mega",
            "houndoom-mega",
            "tyranitar-mega",
            "blaziken-mega",
            "gardevoir-mega",
            "mawile-mega",
            "aggron-mega",
            "medicham-mega",
            "manectric-mega",
            "banette-mega",
            "absol-mega",
            "garchomp-mega",
            "lucario-mega",
            "abomasnow-mega",
            "floette-eternal",
            "latias-mega",
            "latios-mega",
            "swampert-mega",
            "sceptile-mega",
            "sableye-mega",
            "altaria-mega",
            "gallade-mega",
            "audino-mega",
            "sharpedo-mega",
            "slowbro-mega",
            "steelix-mega",
            "pidgeot-mega",
            "glalie-mega",
            "diancie-mega",
            "metagross-mega",
            "kyogre-primal",
            "groudon-primal",
            "rayquaza-mega",
            "pikachu-rock-star",
            "pikachu-belle",
            "pikachu-pop-star",
            "pikachu-phd",
            "pikachu-libre",
            "pikachu-cosplay",
            "hoopa-unbound",
            "camerupt-mega",
            "lopunny-mega",
            "salamence-mega",
            "beedrill-mega"

    };

    String apikey = "1234567890";
    String returntype = "HTML";
    private String[] triggers = {"random","bored"};

    public ArrayList<String> evaluate(String[] input) {
        //String tag = "";
        ArrayList<String> answer = new ArrayList<String>();
        Random r = new Random();
        //If input isnt null, check if it coresponds with the trigger-words
        if(input.length > 0)
        {
            for(int i = 0; i < triggers.length; i++)
            {
                if(input[0].equalsIgnoreCase(triggers[i]))
                {
                    //Check if there could be some other inputs to the function
                    if(input.length > 1)
                    {

                        if(input[1].equalsIgnoreCase("link"))
                        {
                            String[] tags = {"bajs"};
                            String[] result = Lib.readWebsite("http://skbot.snekabel.se/api/v0/getRandomLink.php?apikey="+apikey+"&tags="+tags[0]+"&returntype="+returntype);
                            for(int j = 0; j < result.length; j++)
                            {
                                //System.out.println(result[j]);
                                answer.add(result[j]);
                            }
                        }
                        else if(input[1].equalsIgnoreCase("pokemon"))
                        {

                            int idx = r.nextInt(PokemonData.length);
                            String pokemon = (PokemonData[idx]);
                            answer.add(pokemon);
                        }
                    }
                    // Else just go with the default output
                    else
                    {
                        String[] result = Lib.readWebsite("http://skbot.snekabel.se/api/v0/getRandomLink.php?apikey="+apikey+"&returntype="+returntype);
                        for(int j = 0; j < result.length; j++)
                        {
                            //System.out.println(result[j]);
                            answer.add(result[j]);
                        }
                    }
                }
            }
        }
        //return new ArrayList<String>();
        return answer;
    }

    public String getHelpDescription() {

        return "random - Returns a random link from DB.\n" +
               "random pokemon - Returns a random Pokémon";
    }


    public String getShortDescription() {

        return "Returns a random link";
    }

    public String getName() {
        return name;
    }

    public String[] getTriggers() {
        return triggers;
    }

}
