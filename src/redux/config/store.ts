import { configureStore } from "@reduxjs/toolkit";
import menuSlice from "../slices/menu.slice";
import modalSlice from "../slices/modal.slice";
import toastSlice from "../slices/toast.slice";

const store = configureStore({
  reducer: {
    modal: modalSlice,
    toast: toastSlice,
    menu: menuSlice,
  },
});

export default store;
