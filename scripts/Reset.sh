#!/bin/bash

# Revert skip worktree changes
bash scripts/skipWorktreeReset.sh

# Remove executable permissions from scripts
chmod -x scripts/*

echo "Reset complete."