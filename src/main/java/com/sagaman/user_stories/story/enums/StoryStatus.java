package com.sagaman.user_stories.story.enums;

public enum StoryStatus {
    TODO {
        @Override
        public boolean canTransitionTo(StoryStatus target) {
            return target == IN_PROGRESS || target == CANCELLED;
        }

        @Override
        public boolean canBeCommentedOn() {
            return true;
        }
    },
    IN_PROGRESS {
        @Override
        public boolean canTransitionTo(StoryStatus target) {
            return target == DONE || target == CANCELLED;
        }

        @Override
        public boolean canBeCommentedOn() {
            return true;
        }
    },
    DONE {
        @Override
        public boolean canTransitionTo(StoryStatus target) {
            return false;
        }

        @Override
        public boolean canBeCommentedOn() {
            return false;
        }
    },
    CANCELLED {
        @Override
        public boolean canTransitionTo(StoryStatus target) {
            return false;
        }

        @Override
        public boolean canBeCommentedOn() {
            return false;
        }
    };

    public abstract boolean canTransitionTo(StoryStatus target);

    public abstract boolean canBeCommentedOn();
}
