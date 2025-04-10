package com.danield.javagotchi.entities;

public enum NatureElement {
    FIRE(new String[] { "Pyranthia", "Inferno", "Emberclaw", "Ashstorm", "Lavafist",
                        "Blazetail", "Ignis", "Firebrand", "Phoenix", "Vulcanos",
                        "Red Giant", "Scorch", "Ashenlord", "Pyros", "Sparkwing"}),
    WATER(new String[] {"Aquamarine", "Tidewarden", "Mistripper", "Whirltooth", "Maelstrom",
                        "Wavebreaker", "Siren", "Nymphaea", "Oceanos", "Icekristal",
                        "Floodtide", "Riptide", "Hydros", "Saltwake", "Tearfall"}),
    EARTH(new String[] {"Graniteclaw", "Rockbaron", "Quake", "Mountainking", "Crystaleye",
                        "Stonefist", "Thorncrown", "Titan", "Clayguard", "Sandstorm",
                        "Shalejaw", "Onyx", "Rootweb", "Deeproot", "Emerald"}),
    WIND(new String[] { "Zephyra", "Hurricaneye", "Whirlwind", "Skyherald", "Featherstorm",
                        "Cloudrunner", "Thundersong", "Hawkeye", "Cyclone", "Windspirit",
                        "Swiftwing", "Aether", "Breezefall", "Stormcrow", "Airking"});

    private final String[] names;

    NatureElement(String[] names) {
        this.names = names;
    }

    public String[] getNames(){
        return names;
    }
}
