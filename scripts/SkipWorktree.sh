#!/bin/bash

# Grant executable permissions to the script
chmod +x scripts/SkipWorktree.sh

# Change directory to the root of the Git repository
cd "$(git rev-parse --show-toplevel)" || exit

# Run the git update-index command to skip worktree for user.csv
git update-index --skip-worktree src/main/resources/db/user.csv

# Run the git update-index command to skip worktree for score.csv
git update-index --skip-worktree src/main/resources/db/score.csv

# Optionally, you can add more git update-index commands here for other files if needed
# git update-index --skip-worktree path/to/other/file.csv
