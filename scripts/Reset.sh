#!/bin/bash

echo "Resetting ..."

# Revert skip worktree changes
bash scripts/skipWorktreeReset.sh

# Remove executable permissions from scripts
chmod -x scripts/*

