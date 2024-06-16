import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  modalOptions: null,
};

const modalSlice = createSlice({
  name: "modal",
  initialState,
  reducers: {
    modalOpen: (state, action) => {
      state.modalOptions = action.payload;
    },
    modalClose: (state) => {
      state.modalOptions = null;
    },
  },
});

export const { modalOpen, modalClose } = modalSlice.actions;

export default modalSlice.reducer;
