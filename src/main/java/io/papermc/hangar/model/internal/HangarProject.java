package io.papermc.hangar.model.internal;

import io.papermc.hangar.config.jackson.RequiresPermission;
import io.papermc.hangar.db.customtypes.RoleCategory;
import io.papermc.hangar.model.api.project.Project;
import io.papermc.hangar.model.db.projects.ProjectOwner;
import io.papermc.hangar.model.db.roles.ProjectRoleTable;
import io.papermc.hangar.model.internal.user.JoinableMember;
import io.papermc.hangar.modelold.NamedPermission;

import java.util.List;

public class HangarProject extends Project implements Joinable<ProjectRoleTable> {

    private final ProjectOwner owner;
    private final List<JoinableMember<ProjectRoleTable>> members;
    private final String lastVisibilityChangeComment;
    private final String lastVisibilityChangeUserName;
    private final HangarProjectInfo info;
    
    public HangarProject(Project project, ProjectOwner owner, List<JoinableMember<ProjectRoleTable>> members, String lastVisibilityChangeComment, String lastVisibilityChangeUserName, HangarProjectInfo info) {
        super(project.getCreatedAt(), project.getName(), project.getNamespace(), project.getPromotedVersions(), project.getStats(), project.getCategory(), project.getDescription(), project.getLastUpdated(), project.getVisibility(), project.getUserActions(), project.getSettings());
        this.owner = owner;
        this.members = members;
        this.lastVisibilityChangeComment = lastVisibilityChangeComment;
        this.lastVisibilityChangeUserName = lastVisibilityChangeUserName;
        this.info = info;
    }

    @Override
    public ProjectOwner getOwner() {
        return owner;
    }

    @Override
    public RoleCategory getRoleCategory() {
        return RoleCategory.PROJECT;
    }

    @Override
    public List<JoinableMember<ProjectRoleTable>> getMembers() {
        return members;
    }

    public String getLastVisibilityChangeComment() {
        return lastVisibilityChangeComment;
    }

    public String getLastVisibilityChangeUserName() {
        return lastVisibilityChangeUserName;
    }
    public HangarProjectInfo getInfo() {
        return info;
    }

    public static class HangarProjectInfo {

        private final int publicVersions;
        private final int noteCount;
        private final long starCount;
        private final long watcherCount;

        public HangarProjectInfo(int publicVersions, int noteCount, long starCount, long watcherCount) {
            this.publicVersions = publicVersions;
            this.noteCount = noteCount;
            this.starCount = starCount;
            this.watcherCount = watcherCount;
        }

        public int getPublicVersions() {
            return publicVersions;
        }

        @RequiresPermission(NamedPermission.MOD_NOTES_AND_FLAGS)
        public int getNoteCount() {
            return noteCount;
        }

        public long getStarCount() {
            return starCount;
        }

        public long getWatcherCount() {
            return watcherCount;
        }

        @Override
        public String toString() {
            return "HangarProjectInfo{" +
                    "publicVersions=" + publicVersions +
                    ", noteCount=" + noteCount +
                    ", starCount=" + starCount +
                    ", watcherCount=" + watcherCount +
                    '}';
        }
    }
}