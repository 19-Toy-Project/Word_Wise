import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  menuOption: false,
};

const menuSlice = createSlice({
  name: "menu",
  initialState,
  reducers: {
    menuToggle: (state) => {
      state.menuOption = !state.menuOption;
    },
  },
});

export const { menuToggle } = menuSlice.actions;

export default menuSlice.reducer;
