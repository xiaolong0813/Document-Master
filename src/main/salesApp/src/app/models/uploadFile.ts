export class uploadFile {
  // para name should be aligned with backend
  id : number;
  filename : string;
  savetime : string;

  // uploading -> 1
  // uploaded -> 2

  // parsing -> 3
  // translating -> 4
  // translated -> 5

  // error -> 6
  // caution -> 7
  // pause -> 8
  // cancel -> 9
  status : number;

  percent : number;

  size : number;
  speed : number;
}
