import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  toastOptions: null,
};

const toastSlice = createSlice({
  name: "toast",
  initialState,
  reducers: {
    toastOpen: (state, action) => {
      state.toastOptions = action.payload;
    },
    toastClose: (state) => {
      state.toastOptions = null;
    },
  },
});

export const { toastOpen, toastClose } = toastSlice.actions;

export default toastSlice.reducer;
