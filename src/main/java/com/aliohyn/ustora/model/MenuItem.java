package com.aliohyn.ustora.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String link;

    private String icon;

    @ManyToOne
    @NotNull
    private MenuType menuType;

    @NotNull
    private int sort;

    @ManyToOne
    private MenuItem parent;

    @Transient
    private boolean selected;

    @Transient
    private int depthLevel;

    @Transient
    private boolean hasChildren;


    public MenuItem() {
    }

    public MenuItem(String name, String link, MenuType menuType, Integer sort) {
        this.name = name;
        this.link = link;
        this.menuType = menuType;
        this.sort = sort;
    }

    public MenuItem(String name, String link, MenuType menuType, Integer sort, String icon) {
        this.name = name;
        this.link = link;
        this.menuType = menuType;
        this.sort = sort;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getDepthLevel() {
        return depthLevel;
    }

    public void setDepthLevel(int depthLevel) {
        this.depthLevel = depthLevel;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return sort == menuItem.sort &&
                depthLevel == menuItem.depthLevel &&
                Objects.equals(id, menuItem.id) &&
                Objects.equals(name, menuItem.name) &&
                Objects.equals(link, menuItem.link) &&
                Objects.equals(icon, menuItem.icon) &&
                Objects.equals(menuType, menuItem.menuType) &&
                Objects.equals(parent, menuItem.parent) &&
                Objects.equals(selected, menuItem.selected) &&
                Objects.equals(hasChildren, menuItem.hasChildren);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, link, icon, menuType, sort, parent, selected, depthLevel, hasChildren);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", icon='" + icon + '\'' +
                ", menuType=" + menuType +
                ", sort=" + sort +
                ", parent=" + parent +
                ", selected=" + selected +
                ", depthLevel=" + depthLevel +
                ", hasChildren=" + hasChildren +
                '}';
    }
}
