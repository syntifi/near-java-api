---
title: Tests Summary
author: Alexandre Carvalho
menu_item: true
menu_title: Tests
category: reports
child_category: surefire_reports
layout: default
order: 3
---

# JUnit Reports
{% for version in site.data.versions %}
- [{{version.version}}](versions/tests-results-{{version.version}}.html)
{% endfor %}