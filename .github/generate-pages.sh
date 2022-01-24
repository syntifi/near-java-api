#! /bin/bash
if ! test -n "${VERSIONS_FILE}"; then
    VERSIONS_FILE=_data/versions.csv
fi

if ! test -n "${VERSION_PAGES_LOCATION}"; then
    VERSION_PAGES_LOCATION=pages/versions
fi

if ! test -n "${TEMPLATES_LOCATION}"; then
    TEMPLATES_LOCATION=.github/templates
fi

mkdir _data
mkdir -p $VERSION_PAGES_LOCATION

echo -e "version\nlatest" >$VERSIONS_FILE
find ./docs -maxdepth 1 -type d -printf '%P\n' | grep -P "\d+\.\d+\.\d+.*" | sed '/-/!{s/$/_/}' | sort -rV | sed 's/_$//' >>$VERSIONS_FILE

readarray -t VERSIONS <<<$(cat $VERSIONS_FILE)

rm $VERSION_PAGES_LOCATION/*

idx=0
for version in "${VERSIONS[@]:1}"; do
    for file in $TEMPLATES_LOCATION/*.md; do
        target_file=${file/"$TEMPLATES_LOCATION"/"$VERSION_PAGES_LOCATION"}
        target_file=${target_file/VERSION/"$version"}
        cp $file $target_file
        sed -i s/VERSION/"$version"/g $target_file
        sed -i s/ORDER/"$idx"/g $target_file
        echo "Created page $target_file"
    done
    idx=$((idx + 1))
done
