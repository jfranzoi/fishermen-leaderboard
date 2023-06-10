#!/usr/bin/env bash

function api_get() {
	local path="$1"
	curl -s --location --request GET "https://app.ogyre.com/api/exam/$path" \
		--header 'Authorization: Bearer qe4k8rcmpdkgaicwmvmjfa17grcvav'
}

FISHERMEN=$(api_get "fishermen" | jq -r '.[]._id')
echo "Collected $( echo "$FISHERMEN" | wc -l | xargs ) fishermen"

echo "$FISHERMEN" | while read each; do
	RECOLLECTIONS=$( api_get "recollections/$each" | jq -r '.listOfrecollections | length' )
	echo "Fisherman $each, with $RECOLLECTIONS recollections"
done

echo "Done."