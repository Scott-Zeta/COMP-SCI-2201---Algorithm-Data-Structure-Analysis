# COMP-SCI-2201---Algorithm-Data-Structure-Analysis_a4

## What

Problem itself is an MST problem variation

## How

Use whatever Kruskal or Prim. Main issue is a combined routing table:
If the edge doesn't exist, we need pick the cheapest one.
If the edge exist, we need to pick the most expensive one. Or we will bleed when destrcuting.
So, use negative number for exist edge as weight.

## Highlighted Difficulties

Write in C++ as Uni required.
Test for SSH Push
