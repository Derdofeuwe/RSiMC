package com.derdofeuwe.rsimcmod.skill.guide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SkillGuide {
    public static final String CATEGORY_BASIC = "Basic";
    private HashMap<String, List<Guide>> objectsByCategory = new HashMap();
    private List<String> categories = new ArrayList<String>();

    public void clearGuides(String category) {
        this.categories.remove(category);
        this.objectsByCategory.remove(category);
    }

    public void clearGuides() {
        this.categories.clear();
        this.objectsByCategory.clear();
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void addGuideObject(Guide object) {
        this.addGuideObject(CATEGORY_BASIC, object);
    }

    public void addGuideObjects(Iterable<Guide> objects) {
        for (Guide object : objects) {
            this.addGuideObject(object);
        }
    }

    public void addGuideObject(String category, Guide object) {
        if (!this.objectsByCategory.containsKey(category)) {
            this.categories.add(category);
            this.objectsByCategory.put(category, new ArrayList());
        }
        this.objectsByCategory.get(category).add(object);
        Collections.sort(this.getGuideObjectsFromCategory(category));
    }

    public void addGuideObjects(String category, Iterable<Guide> objects) {
        for (Guide object : objects) {
            this.addGuideObject(category, object);
        }
    }

    public List<Guide> getGuideObjects() {
        return this.getGuideObjectsFromCategory(CATEGORY_BASIC);
    }

    public List<Guide> getGuideObjectsFromCategory(String category) {
        return this.objectsByCategory.get(category);
    }
}
