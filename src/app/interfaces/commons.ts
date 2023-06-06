export interface Field {
  id ?: number ,
  defaultValue: string,
  name: string,
  min: number,
  isMandatory: "Y" | "N",
  label: string,
  max: number,
  placeholder: string,
  toolTip: string,
  type: "text" | "number" | "date",
  screenId: number,
  isEnabled: "Y" | "N",
  isHidden: "Y" | "N",
  isReadOnly: "Y" | "N"
}

export interface Screen {
  id ?: number,
  screenName: string,
  shortDescription:string,
  description:string,
  module:number,
  fields : Field []
}
