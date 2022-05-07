package com.example.zooseeker;

import android.nfc.Tag;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanguageConverter {
    @TypeConverter
    public TagList storedStringToLanguages(String tag) {
        List<String> langs = new ArrayList(Arrays.asList(tag.split("\\s*,\\s*")));
        return new TagList(langs);
    }

    @TypeConverter
    public String languagesToStoredString(TagList tags) {
        String tag = "";
        for (String lang : tags.getTagList())
            tag += lang + ",";
        return tag;
    }
}
