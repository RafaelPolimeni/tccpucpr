/*******************************************************************************
* ' Name...........: limitText ' ' Goal...........: Limit the length of a
* textarea ' ' Parameters.....: field - textarea ' maxLength - max length of
* the field ' ' Observation....: ' ' Way to use:
* onkeyup="javascript:limitText(this, 10);"
******************************************************************************/
function limitText(field, maxLength) {
	if (field.value.length > maxLength) {
		field.value = field.value.substring(0, maxLength);
	}
}