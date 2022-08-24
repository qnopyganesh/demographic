import { CoreMenu } from "@core/types";

export const menu: CoreMenu[] = [
  {
    id: "home",
    title: "Home",
    translate: "MENU.HOME",
    type: "item",
    icon: "home",
    url: "home",
  },
  {
    id: "sample",
    title: "Sample",
    translate: "MENU.SAMPLE",
    type: "item",
    icon: "file",
    url: "sample",
  },
  {
    id: "encode",
    title: "Encode",
    translate: "Encode",
    type: "item",
    icon: "code",
    url: "encode",
  },
  {
    id: "search",
    title: "Search",
    translate: "Search",
    type: "item",
    icon: "search",
    url: "search",
  },
 
  {
    id: "uploadScreen",
    title: "UploadScreen",
    translate: "Upload",
    type: "item",
    icon: "upload",
    url: "uploadscreen",
  },
];
