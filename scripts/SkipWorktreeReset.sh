#!/bin/bash

# Grant executable permissions to the SkipWorktree.sh script
chmod +x scripts/SkipWorktree.sh

# Execute the SkipWorktree.sh script
./scripts/SkipWorktree.sh

# Revert changes made by SkipWorktree.sh script
git update-index --no-skip-worktree src/main/resources/db/user.csv
git update-index --no-skip-worktree src/main/resources/db/score.csv

# Optionally, you can revert changes for other files if needed
# git update-index --no-skip-worktree path/to/other/file.csv

echo "Skip worktree changes reset successfully."
