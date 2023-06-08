export interface SelectOption {
  id?: number,
  value: string,
  text: string | null,
  isSelected: "Y" | "N",
  isHidden: "Y" | "N",
  isEnabled: "Y" | "N"
}

export interface Field {
  id?: number,
  defaultValue: string,
  name: string,
  min: number,
  isMandatory: "Y" | "N",
  label: string,
  max: number,
  placeholder: string,
  toolTip: string,
  type: "text" | "number" | "date" | "checkout" | "select",
  formId: number,
  isEnabled: "Y" | "N",
  isHidden: "Y" | "N",
  isReadOnly: "Y" | "N",
  selectOptions: SelectOption[]
}

export interface Form {
  id?: number,
  name: string,
  shortDescription: string,
  description: string,
  module: number,
  fields: Field []
}
