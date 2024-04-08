#!/bin/bash

# Revert changes made by SkipWorktree.sh script
git update-index --no-skip-worktree src/main/resources/db/user.csv
git update-index --no-skip-worktree src/main/resources/db/score.csv

# Optionally, you can revert changes for other files if needed
# git update-index --no-skip-worktree path/to/other/file.csv
