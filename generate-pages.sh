#! /bin/bash
if ! test -n "${MASTER_BRANCH_NAME}"; then
    MASTER_BRANCH_NAME=origin/main
fi

if ! test -n "${SOURCE_INDEX_FILE}"; then
    SOURCE_INDEX_FILE=README.md
fi

SOURCE_TEMPLATES_LOCATION=./templates
TARGET_PAGES_LOCATION=./pages
TARGET_VERSIONS_FILE=./_data/versions.csv

mkdir _data
mkdir -p $TARGET_PAGES_LOCATION/versions

rm $TARGET_PAGES_LOCATION/*

# Copy base files
echo "Checking out $SOURCE_INDEX_FILE from $MASTER_BRANCH_NAME"
git checkout $MASTER_BRANCH_NAME -- $SOURCE_INDEX_FILE
cp -f $SOURCE_TEMPLATES_LOCATION/.gitignore .
cp -f $SOURCE_TEMPLATES_LOCATION/_config.yml .
cp -f $SOURCE_TEMPLATES_LOCATION/Gemfile .
cp -f $SOURCE_TEMPLATES_LOCATION/pages/index.md .
find $SOURCE_TEMPLATES_LOCATION/pages/  -maxdepth 1 ! -name index.md -exec cp -t $TARGET_PAGES_LOCATION/ {} +
cat $SOURCE_INDEX_FILE >> index.md

echo -e "version\nlatest" > $TARGET_VERSIONS_FILE
find ./docs -maxdepth 1 -type d -printf '%P\n' | grep -P "\d+\.\d+\.\d+.*" | sed '/-/!{s/$/_/}' | sort -rV | sed 's/_$//' >>$TARGET_VERSIONS_FILE

readarray -t VERSIONS <<< "$(cat $TARGET_VERSIONS_FILE)"

idx=0
for version in "${VERSIONS[@]:1}"; do
    for file in "$SOURCE_TEMPLATES_LOCATION"/pages/versions/*.md; do
        target_file=${file/"$SOURCE_TEMPLATES_LOCATION/pages/versions"/"$TARGET_PAGES_LOCATION/versions"}
        target_file=${target_file/VERSION/"$version"}
        cp "$file" "$target_file"
        sed -i s/VERSION/"$version"/g "$target_file"
        sed -i s/ORDER/"$idx"/g "$target_file"
        echo "Created page $target_file"
    done
    idx=$((idx + 1))
done

git rm -f -r $SOURCE_INDEX_FILE

rm -rf $SOURCE_INDEX_FILE
