export const formatAMPM = (date: Date): string => {
  let hours = date.getHours();
  const minutes = date.getMinutes();
  const ampm = hours >= 12 ? "오후" : "오전";
  hours = hours % 12;
  hours = hours ? hours : 12; // the hour '0' should be '12'
  const minute = minutes < 10 ? "0" + toString() : minutes;
  return `${ampm} ${hours}:${minute}`;
};
